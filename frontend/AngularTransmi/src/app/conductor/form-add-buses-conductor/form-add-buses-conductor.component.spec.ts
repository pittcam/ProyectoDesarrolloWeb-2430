import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAddBusesConductorComponent } from './form-add-buses-conductor.component';

describe('FormAddBusesConductorComponent', () => {
  let component: FormAddBusesConductorComponent;
  let fixture: ComponentFixture<FormAddBusesConductorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormAddBusesConductorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormAddBusesConductorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
