import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAddRutaComponent } from './form-add-ruta.component';

describe('FormAddRutaComponent', () => {
  let component: FormAddRutaComponent;
  let fixture: ComponentFixture<FormAddRutaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormAddRutaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormAddRutaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
