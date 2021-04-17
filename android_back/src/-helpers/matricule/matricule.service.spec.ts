import { Test, TestingModule } from '@nestjs/testing';
import { MatriculeService } from './matricule.service';

describe('MatriculeService', () => {
  let service: MatriculeService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [MatriculeService],
    }).compile();

    service = module.get<MatriculeService>(MatriculeService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });
});
