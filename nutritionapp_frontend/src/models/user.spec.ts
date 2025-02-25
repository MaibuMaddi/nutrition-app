import { User } from './user';

describe('User', () => {
  it('should create an instance', () => {
    const user = new User('testUsername', 'testPassword', 'test@example.com');
    expect(user).toBeTruthy();
  });
});
