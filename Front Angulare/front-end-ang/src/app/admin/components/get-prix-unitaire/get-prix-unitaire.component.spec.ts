import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetPrixUnitaireComponent } from './get-prix-unitaire.component';

describe('GetPrixUnitaireComponent', () => {
  let component: GetPrixUnitaireComponent;
  let fixture: ComponentFixture<GetPrixUnitaireComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetPrixUnitaireComponent]
    });
    fixture = TestBed.createComponent(GetPrixUnitaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
