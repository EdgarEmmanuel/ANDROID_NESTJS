import { AccessMiddleware } from './access.middleware';

describe('AccessMiddleware', () => {
  it('should be defined', () => {
    expect(new AccessMiddleware()).toBeDefined();
  });
});
