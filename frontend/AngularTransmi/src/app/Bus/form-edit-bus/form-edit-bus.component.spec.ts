import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormEditBusComponent } from './form-edit-bus.component';

describe('FormEditBusComponent', () => {
  let component: FormEditBusComponent;
  let fixture: ComponentFixture<FormEditBusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormEditBusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormEditBusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
