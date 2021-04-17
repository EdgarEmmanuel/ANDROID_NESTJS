import { IsEmail, Length } from 'class-validator';
import { BaseEntity, Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class User extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  @Length(10, 23)
  name: string;

  @Column()
  @Length(10, 23)
  surname: string;

  @Column()
  @IsEmail()
  email: string;

  @Column()
  @Length(9, 11)
  phone: string;

  @Column()
  @Length(10, 23)
  password: string;

  @Column()
  @Length(10, 23)
  country: string;

  @Column()
  @Length(10, 23)
  matricule: string;

  @Column()
  age: number;
}
