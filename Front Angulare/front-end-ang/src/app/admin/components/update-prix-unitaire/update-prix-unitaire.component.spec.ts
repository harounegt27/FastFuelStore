import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePrixUnitaireComponent } from './update-prix-unitaire.component';

describe('UpdatePrixUnitaireComponent', () => {
  let component: UpdatePrixUnitaireComponent;
  let fixture: ComponentFixture<UpdatePrixUnitaireComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdatePrixUnitaireComponent]
    });
    fixture = TestBed.createComponent(UpdatePrixUnitaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
