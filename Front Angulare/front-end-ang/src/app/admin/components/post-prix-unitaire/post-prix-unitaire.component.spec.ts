import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostPrixUnitaireComponent } from './post-prix-unitaire.component';

describe('PostPrixUnitaireComponent', () => {
  let component: PostPrixUnitaireComponent;
  let fixture: ComponentFixture<PostPrixUnitaireComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostPrixUnitaireComponent]
    });
    fixture = TestBed.createComponent(PostPrixUnitaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
