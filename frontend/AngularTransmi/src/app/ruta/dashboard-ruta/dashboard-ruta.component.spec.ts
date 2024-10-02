import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardRutaComponent } from './dashboard-ruta.component';

describe('DashboardRutaComponent', () => {
  let component: DashboardRutaComponent;
  let fixture: ComponentFixture<DashboardRutaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardRutaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardRutaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
