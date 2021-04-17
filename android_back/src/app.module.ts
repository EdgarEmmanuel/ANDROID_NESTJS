import { AccessMiddleware } from './-middleware/access.middleware';
import {
  Module,
  NestModule,
  MiddlewareConsumer,
  RequestMethod,
} from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { UserModule } from './user/user.module';
import { CompteModule } from './compte/compte.module';
import { TransfertModule } from './transfert/transfert.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AuthModule } from './auth/auth/auth.module';
import { HelpersService } from './-helpers/helpers/helpers.service';
import { MatriculeService } from './-helpers/matricule/matricule.service';

@Module({
  imports: [
    TypeOrmModule.forRoot(),
    UserModule,
    CompteModule,
    TransfertModule,
    AuthModule,
  ],
  controllers: [AppController],
  providers: [AppService, HelpersService, MatriculeService],
})
export class AppModule implements NestModule {
  configure(consumer: MiddlewareConsumer) {
    consumer
      .apply(AccessMiddleware)
      .exclude(
        { path: 'user/login', method: RequestMethod.POST },
        { path: 'user/refresh', method: RequestMethod.POST },
        { path: 'user', method: RequestMethod.POST },
      )
      .forRoutes(
        { path: 'user', method: RequestMethod.ALL },
        { path: 'transfert', method: RequestMethod.ALL },
        { path: 'compte', method: RequestMethod.ALL },
      );
  }
}
