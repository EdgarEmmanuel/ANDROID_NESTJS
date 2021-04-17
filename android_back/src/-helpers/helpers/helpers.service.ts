import { Injectable } from '@nestjs/common';

@Injectable()
export class HelpersService {
  async generateMatriculeTransfert(id: number) {
    return await `TR${id}TYBD`;
  }
}
