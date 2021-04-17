import { Compte } from './entities/compte.entity';
import {
  Controller,
  Get,
  Post,
  Body,
  Put,
  Param,
  Delete,
} from '@nestjs/common';
import { CompteService } from './compte.service';

@Controller('compte')
export class CompteController {
  constructor(private readonly compteService: CompteService) {}

  @Post()
  create(@Body() compte: Compte) {
    return this.compteService.create(compte);
  }

  @Get()
  findAll() {
    return this.compteService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.compteService.findOne(+id);
  }

  @Get('info/:id')
  findCompteByUserId(@Param('id') id: string) {
    return this.compteService.findByUserId(+id);
  }

  @Put(':id')
  update(@Param('id') id: string, @Body() compte: Compte) {
    return this.compteService.update(+id, compte);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.compteService.remove(+id);
  }
}
