import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardConsultarRutaComponent } from './dashboard-consultar-ruta.component';

describe('DashboardConsultarRutaComponent', () => {
  let component: DashboardConsultarRutaComponent;
  let fixture: ComponentFixture<DashboardConsultarRutaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardConsultarRutaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardConsultarRutaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
