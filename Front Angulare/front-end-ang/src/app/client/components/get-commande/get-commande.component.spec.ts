import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCommandeComponent } from './get-commande.component';

describe('GetCommandeComponent', () => {
  let component: GetCommandeComponent;
  let fixture: ComponentFixture<GetCommandeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetCommandeComponent]
    });
    fixture = TestBed.createComponent(GetCommandeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
