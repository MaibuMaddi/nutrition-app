import { UserCredentials } from './user-credentials';




describe('UserCredentials', () => {
  it('should create an instance', () => {
    const userCredentials = new UserCredentials('testUsername', 'testPassword');
    expect(userCredentials).toBeTruthy();
  });

  

  
});
