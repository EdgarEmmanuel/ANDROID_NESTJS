import { Compte } from './entities/compte.entity';
import { Injectable } from '@nestjs/common';
// import { CreateCompteDto } from './dto/create-compte.dto';
// import { UpdateCompteDto } from './dto/update-compte.dto';

@Injectable()
export class CompteService {
  async create(compte: Compte) {
    try {
      return await Compte.save(compte);
    } catch (error) {
      return await { message: error };
    }
  }

  async findAll() {
    try {
      return await Compte.find();
    } catch (error) {
      return await { message: error };
    }
  }

  async findOne(id: number) {
    try {
      return await Compte.findOne(id);
    } catch (error) {
      return await { message: error };
    }
  }

  async findByUserId(idUser: number) {
    try {
      return await Compte.find({ where: { user: idUser } });
    } catch (error) {
      return { message: error, success: false };
    }
  }

  async update(id: number, compte: Compte) {
    try {
      return await Compte.update(id, compte);
    } catch (error) {
      return await { message: error };
    }
  }

  async remove(id: number) {
    try {
      return await Compte.delete(id);
    } catch (error) {
      return await { message: error };
    }
  }
}
