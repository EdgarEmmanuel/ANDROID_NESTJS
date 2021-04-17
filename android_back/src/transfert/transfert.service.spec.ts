import { Test, TestingModule } from '@nestjs/testing';
import { TransfertService } from './transfert.service';

describe('TransfertService', () => {
  let service: TransfertService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [TransfertService],
    }).compile();

    service = module.get<TransfertService>(TransfertService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
