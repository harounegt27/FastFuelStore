import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetProduitsCommandeComponent } from './get-produits-commande.component';

describe('GetProduitsCommandeComponent', () => {
  let component: GetProduitsCommandeComponent;
  let fixture: ComponentFixture<GetProduitsCommandeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetProduitsCommandeComponent]
    });
    fixture = TestBed.createComponent(GetProduitsCommandeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
