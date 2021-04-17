import { PartialType } from '@nestjs/mapped-types';
import { CreateCompteDto } from './create-compte.dto';

export class UpdateCompteDto extends PartialType(CreateCompteDto) {}
