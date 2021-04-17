import { Test, TestingModule } from '@nestjs/testing';
import { TransfertController } from './transfert.controller';
import { TransfertService } from './transfert.service';

describe('TransfertController', () => {
  let controller: TransfertController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [TransfertController],
      providers: [TransfertService],
    }).compile();

    controller = module.get<TransfertController>(TransfertController);
  });

  it('should be defined', () => {
    expect(controller).toBeDefined();
  });
});
