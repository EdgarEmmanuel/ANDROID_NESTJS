import { Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { User } from 'src/user/entities/user.entity';
import * as bcrypt from 'bcrypt';

@Injectable()
export class AuthService {
  constructor(private jwtService: JwtService) {}

  async generateJWT(payload: User) {
    return await this.jwtService.sign({ user: payload }, { expiresIn: '3h' });
  }

  async generateRefreshToken(payload: User) {
    return await this.jwtService.sign(
      { user: payload },
      { secret: 'test', expiresIn: '5h' },
    );
  }

  async hashPassword(password: string) {
    return await bcrypt.hash(password, 12);
  }

  async comparePasswords(password: string, Encryptpassword: string) {
    return await bcrypt.compare(password, Encryptpassword);
  }

  async verifyJWT(token: string) {
    return await this.jwtService.verify(token);
  }

  async verifyRefreshJWT(token: string, secretOfToken: string) {
    return await this.jwtService.verify(token, { secret: secretOfToken });
  }

  async decodeJWT(token: string) {
    return await this.jwtService.decode(token);
  }

  removeBearerInToken(token: string) {
    let VALID_TOKEN = '';
    const ARRAY = [0, 1, 2, 3, 4, 5, 6];
    for (let i = 0; i < token.length; i++) {
      if (!ARRAY.includes(i)) VALID_TOKEN += token[i];
    }

    return VALID_TOKEN;
  }
}
