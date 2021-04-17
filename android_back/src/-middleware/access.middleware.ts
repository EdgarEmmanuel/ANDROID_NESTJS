//import { Length } from 'class-validator';
import { AuthService } from './../auth/auth/auth.service';
import { Injectable, NestMiddleware } from '@nestjs/common';

@Injectable()
export class AccessMiddleware implements NestMiddleware {
  constructor(private _jwtService: AuthService) {}
  async use(req: any, res: any, next: () => void) {
    if (req.headers.authorization) {
      try {
        const VALID_TOKEN = this._jwtService.removeBearerInToken(
          req.headers.authorization,
        );
        //wait the treatment of the token
        await this._jwtService.verifyJWT(VALID_TOKEN);
        //if all is good continue
        next();
      } catch (error) {
        return await res.send({
          statusCode: 403,
          erreur: 'LE TOKEN EST INVALIDE VOUS DEVRIEZ VOUS RECONNECTER',
        });
      }
    } else {
      return await res.send({
        statusCode: 403,
        erreur: 'POUR CETTE ROUTE UN TOKEN DOIT ETRE FOURNIE',
      });
    }
  }
}
