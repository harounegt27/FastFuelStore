import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetWishlistComponent } from './get-wishlist.component';

describe('GetWishlistComponent', () => {
  let component: GetWishlistComponent;
  let fixture: ComponentFixture<GetWishlistComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetWishlistComponent]
    });
    fixture = TestBed.createComponent(GetWishlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
