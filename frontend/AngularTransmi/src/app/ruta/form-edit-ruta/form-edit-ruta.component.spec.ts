import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormEditRutaComponent } from './form-edit-ruta.component';

describe('FormEditRutaComponent', () => {
  let component: FormEditRutaComponent;
  let fixture: ComponentFixture<FormEditRutaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormEditRutaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormEditRutaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
