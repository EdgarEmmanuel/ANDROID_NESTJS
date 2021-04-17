import { MatriculeService } from './../-helpers/matricule/matricule.service';
import { AuthService } from './../auth/auth/auth.service';
import { User } from './entities/user.entity';
import { Injectable } from '@nestjs/common';

@Injectable()
export class UserService {
  constructor(
    private authservice: AuthService,
    private matriculeService: MatriculeService,
  ) {}
  async create(user: User) {
    /**
     * JSON format to send data
     * {
        "email":"mmnledgar@gmail.com",
        "password":"passer",
        "name":"d",
        "surname":"d",
        "phone":"+321431413152",
        "country":"senegal",
        "matricule":"jgkgkg",
        "age":12
    }
    */
    /**
     * get the generated matricule
     */
    user.matricule = await this.matriculeService.generateMatricule();

    /**
     * get the hashed passsword
     */
    user.password = await this.authservice.hashPassword(user.password);
    return await User.save(user);
  }

  async findAll() {
    return await User.find();
  }

  async findOne(id: number) {
    return await User.find<User>({ id: id });
  }

  remove(id: number) {
    try {
      User.delete({ id: id });
      return true;
    } catch (error) {
      return false;
    }
  }

  async update(id: number, user: User) {
    try {
      user.password = await this.authservice.hashPassword(user.password);
      return await User.update(id, user);
    } catch (error) {
      return await error;
    }
  }

  /**
   * function to verify the refresh token
   * @param refreshToken
   */
  async verifyRefreshToken(refreshToken: string) {
    try {
      //verify the token is trusted
      await this.authservice.verifyRefreshJWT(refreshToken, 'test');

      //decode the token
      const data: any = await this.authservice.decodeJWT(refreshToken);

      // get the email of the user
      const EMAIL_USER = data.user.email;

      return await User.findOne({ email: EMAIL_USER }).then(
        async (data: any) => {
          //generate the token
          const token = await this.authservice.generateJWT(data);

          //generate refresh token
          const refreshToken = await this.authservice.generateRefreshToken(
            data,
          );

          //return the response
          return await {
            refreshToken: refreshToken,
            token: token,
            expiresIn: '3h',
            success: true,
          };
        },
      );
      return await { test: 'success' };
    } catch (error) {
      return await { success: 'failed' };
    }
  }

  async login(email: string, password: string) {
    try {
      return await User.findOne({ email: email }).then(async (data: any) => {
        //console.log(data);
        if (await this.authservice.comparePasswords(password, data.password)) {
          //generate the token
          const token = await this.authservice.generateJWT(data);

          //generate refresh token
          const refreshToken = await this.authservice.generateRefreshToken(
            data,
          );

          //return the response
          return await {
            refreshToken: refreshToken,
            token: token,
            expiresIn: '3h',
            success: true,
          };
        } else {
          return await { message: 'user not found', success: false };
        }
      });
    } catch (error) {
      return await { message: error.message };
    }
  }
}
