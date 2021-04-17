import { User } from './../../user/entities/user.entity';
import {
  BaseEntity,
  Column,
  Entity,
  JoinColumn,
  ManyToOne,
  PrimaryGeneratedColumn,
} from 'typeorm';
import { IsDate } from 'class-validator';

@Entity()
export class Transfert extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  matricule: string;

  @Column()
  type: string;

  @Column()
  montant: number;

  @Column()
  @IsDate()
  dateTransaction: Date;

  @ManyToOne(() => User, { eager: true })
  @JoinColumn()
  emetteur: User;

  @ManyToOne(() => User, { eager: true })
  @JoinColumn()
  recepteur: User;
}
