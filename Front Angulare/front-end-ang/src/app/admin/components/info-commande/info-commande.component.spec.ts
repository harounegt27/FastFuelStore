import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoCommandeComponent } from './info-commande.component';

describe('InfoCommandeComponent', () => {
  let component: InfoCommandeComponent;
  let fixture: ComponentFixture<InfoCommandeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InfoCommandeComponent]
    });
    fixture = TestBed.createComponent(InfoCommandeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
