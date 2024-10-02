import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerEstacionesComponent } from './ver-estaciones.component';

describe('VerEstacionesComponent', () => {
  let component: VerEstacionesComponent;
  let fixture: ComponentFixture<VerEstacionesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerEstacionesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerEstacionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
