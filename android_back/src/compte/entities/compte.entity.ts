import { User } from './../../user/entities/user.entity';
import {
  BaseEntity,
  Column,
  Entity,
  JoinColumn,
  OneToOne,
  PrimaryGeneratedColumn,
} from 'typeorm';
import { IsDate } from 'class-validator';

@Entity()
export class Compte extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  solde: number;

  @Column()
  @IsDate()
  dateCreation: Date;

  @OneToOne(() => User, { eager: true })
  @JoinColumn()
  user: User;
}
