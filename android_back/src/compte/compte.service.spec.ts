import { Test, TestingModule } from '@nestjs/testing';
import { CompteService } from './compte.service';

describe('CompteService', () => {
  let service: CompteService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [CompteService],
    }).compile();

    service = module.get<CompteService>(CompteService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
