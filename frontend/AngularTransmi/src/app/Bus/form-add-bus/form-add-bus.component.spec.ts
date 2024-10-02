import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAddBusComponent } from './form-add-bus.component';

describe('FormAddBusComponent', () => {
  let component: FormAddBusComponent;
  let fixture: ComponentFixture<FormAddBusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormAddBusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormAddBusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
