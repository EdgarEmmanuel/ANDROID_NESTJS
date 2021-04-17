import { HelpersService } from './../-helpers/helpers/helpers.service';
import { Transfert } from './entities/transfert.entity';
import { Injectable } from '@nestjs/common';

@Injectable()
export class TransfertService {
  constructor(private _helperService: HelpersService) {}
  CountTransfert() {
    Transfert.count();
  }

  async create(transfert: Transfert) {
    //find the number of users
    console.log(`number : ${this.CountTransfert()}`);
    try {
      transfert.matricule = await this._helperService.generateMatriculeTransfert(
        1,
      );
      return await Transfert.save(transfert);
    } catch (error) {
      return await { message: error };
    }
  }

  async findAll() {
    try {
      return await Transfert.find();
    } catch (error) {
      return await { message: error };
    }
  }

  async findOne(id: number) {
    try {
      return await Transfert.findOne(id);
    } catch (error) {
      return await { message: error };
    }
  }
  /**
   * get the all the transfert where the emetteurId matches the param
   * @param idUser
   */
  async findTransfertWhereUserIsEmetteur(idEmetteur: number) {
    try {
      return await Transfert.find({ where: { emetteur: idEmetteur } });
    } catch (error) {
      return { message: error, success: false };
    }
  }
  /**
   * query to get the all the transfert where the recepteurId matches the param
   * @param idUser
   */
  async findTransfertWhereUserIsRecepteur(idRecepteur: number) {
    try {
      return await Transfert.find({ where: { recepteur: idRecepteur } });
    } catch (error) {
      return { message: error, success: false };
    }
  }

  async update(id: number, transfert: Transfert) {
    try {
      return await Transfert.update(id, transfert);
    } catch (error) {
      return await { message: error };
    }
  }

  async remove(id: number) {
    try {
      return await Transfert.delete(id);
    } catch (error) {
      return await { message: error };
    }
  }
}
