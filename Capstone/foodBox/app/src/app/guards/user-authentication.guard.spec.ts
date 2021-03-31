import { TestBed } from '@angular/core/testing';

import { UserAuthenticationGuard } from './user-authentication.guard';

describe('UserAuthenticationGuard', () => {
  let guard: UserAuthenticationGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(UserAuthenticationGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
