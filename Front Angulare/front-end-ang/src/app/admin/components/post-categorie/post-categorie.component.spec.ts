import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostCategorieComponent } from './post-categorie.component';

describe('PostCategorieComponent', () => {
  let component: PostCategorieComponent;
  let fixture: ComponentFixture<PostCategorieComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostCategorieComponent]
    });
    fixture = TestBed.createComponent(PostCategorieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
