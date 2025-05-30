import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostStockComponent } from './post-stock.component';

describe('PostStockComponent', () => {
  let component: PostStockComponent;
  let fixture: ComponentFixture<PostStockComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostStockComponent]
    });
    fixture = TestBed.createComponent(PostStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
