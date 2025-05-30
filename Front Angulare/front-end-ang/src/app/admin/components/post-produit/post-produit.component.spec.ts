import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostProduitComponent } from './post-produit.component';

describe('PostProduitComponent', () => {
  let component: PostProduitComponent;
  let fixture: ComponentFixture<PostProduitComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostProduitComponent]
    });
    fixture = TestBed.createComponent(PostProduitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
