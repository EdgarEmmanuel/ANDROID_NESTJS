import { User } from './../../user/entities/user.entity';
import { Injectable } from '@nestjs/common';

@Injectable()
export class MatriculeService {
  async generateMatricule() {
    let number = 0;
    await User.count().then((data) => (number = data));
    return await `Y${Date.now()}B${number + 1}${Date.UTC(2020, 3)}DH`;
  }
}
