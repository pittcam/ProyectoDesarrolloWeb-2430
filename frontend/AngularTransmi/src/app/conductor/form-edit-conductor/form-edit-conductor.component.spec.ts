import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormEditConductorComponent } from './form-edit-conductor.component';

describe('FormEditConductorComponent', () => {
  let component: FormEditConductorComponent;
  let fixture: ComponentFixture<FormEditConductorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormEditConductorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormEditConductorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
