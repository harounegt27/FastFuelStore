import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviwProduitCommanderComponent } from './reviw-produit-commander.component';

describe('ReviwProduitCommanderComponent', () => {
  let component: ReviwProduitCommanderComponent;
  let fixture: ComponentFixture<ReviwProduitCommanderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReviwProduitCommanderComponent]
    });
    fixture = TestBed.createComponent(ReviwProduitCommanderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
