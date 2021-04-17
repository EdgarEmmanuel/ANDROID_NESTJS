import { PartialType } from '@nestjs/mapped-types';
import { CreateTransfertDto } from './create-transfert.dto';

export class UpdateTransfertDto extends PartialType(CreateTransfertDto) {}
