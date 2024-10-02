import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAddConductorComponent } from './form-add-conductor.component';

describe('FormAddConductorComponent', () => {
  let component: FormAddConductorComponent;
  let fixture: ComponentFixture<FormAddConductorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormAddConductorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormAddConductorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
