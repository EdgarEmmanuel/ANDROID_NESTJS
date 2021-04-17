import { MatriculeService } from './../-helpers/matricule/matricule.service';
import { Module } from '@nestjs/common';
import { UserService } from './user.service';
import { UserController } from './user.controller';
import { AuthModule } from 'src/auth/auth/auth.module';

@Module({
  imports: [AuthModule],
  controllers: [UserController],
  providers: [UserService, MatriculeService],
})
export class UserModule {}
