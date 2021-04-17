import { Transfert } from './entities/transfert.entity';
import {
  Controller,
  Get,
  Post,
  Body,
  Put,
  Param,
  Delete,
} from '@nestjs/common';
import { TransfertService } from './transfert.service';

@Controller('transfert')
export class TransfertController {
  constructor(private readonly transfertService: TransfertService) {}

  @Post()
  create(@Body() transfert: Transfert) {
    return this.transfertService.create(transfert);
  }

  @Get()
  findAll() {
    return this.transfertService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.transfertService.findOne(+id);
  }

  @Put(':id')
  update(@Param('id') id: string, @Body() transfert: Transfert) {
    return this.transfertService.update(+id, transfert);
  }

  /**
   * get the all the transfert where the emetteurId matches the param
   * @param idUser
   */
  @Get('/emetteur/:id')
  findWhereUserIdIsEmetteur(@Param('id') idUser: string) {
    return this.transfertService.findTransfertWhereUserIsEmetteur(+idUser);
  }

  /**
   * get the all the transfert where the recepteurId matches the param
   * @param idUser
   */
  @Get('recepteur/:id')
  findTransfertWhereUserIsRecepteur(@Param('id') idRecepteur: string) {
    return this.transfertService.findTransfertWhereUserIsRecepteur(
      +idRecepteur,
    );
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.transfertService.remove(+id);
  }
}
