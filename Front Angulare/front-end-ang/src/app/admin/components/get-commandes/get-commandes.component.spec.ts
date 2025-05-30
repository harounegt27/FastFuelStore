import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCommandesComponent } from './get-commandes.component';

describe('GetCommandesComponent', () => {
  let component: GetCommandesComponent;
  let fixture: ComponentFixture<GetCommandesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetCommandesComponent]
    });
    fixture = TestBed.createComponent(GetCommandesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
