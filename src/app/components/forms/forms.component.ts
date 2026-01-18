import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';

// Interface for form data
interface UserFormData {
  name: string;
  email: string;
  password: string;
  phone: string;
  address: string;
}

// Interface for form submission result
interface FormSubmitResult {
  success: boolean;
  message: string;
  data?: UserFormData;
}

@Component({
  selector: 'app-forms',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <div class="form-container">
      <div class="form-wrapper">
        <h2 class="form-title">User Registration Form</h2>
        
        <form [formGroup]="userForm" (ngSubmit)="onSubmit()" class="user-form">
          
          <!-- Name Field -->
          <div class="form-group">
            <label for="name" class="form-label">
              Full Name <span class="required">*</span>
            </label>
            <input
              id="name"
              type="text"
              formControlName="name"
              class="form-control"
              [class.error]="isFieldInvalid('name')"
              placeholder="Enter your full name"
            />
            <div class="error-messages" *ngIf="isFieldInvalid('name')">
              <small *ngIf="userForm.get('name')?.errors?.['required']">
                Name is required
              </small>
              <small *ngIf="userForm.get('name')?.errors?.['minlength']">
                Name must be at least 3 characters long
              </small>
              <small *ngIf="userForm.get('name')?.errors?.['maxlength']">
                Name cannot exceed 50 characters
              </small>
            </div>
          </div>

          <!-- Email Field -->
          <div class="form-group">
            <label for="email" class="form-label">
              Email Address <span class="required">*</span>
            </label>
            <input
              id="email"
              type="email"
              formControlName="email"
              class="form-control"
              [class.error]="isFieldInvalid('email')"
              placeholder="Enter your email"
            />
            <div class="error-messages" *ngIf="isFieldInvalid('email')">
              <small *ngIf="userForm.get('email')?.errors?.['required']">
                Email is required
              </small>
              <small *ngIf="userForm.get('email')?.errors?.['email']">
                Please enter a valid email address
              </small>
            </div>
          </div>

          <!-- Password Field -->
          <div class="form-group">
            <label for="password" class="form-label">
              Password <span class="required">*</span>
            </label>
            <input
              id="password"
              type="password"
              formControlName="password"
              class="form-control"
              [class.error]="isFieldInvalid('password')"
              placeholder="Enter your password"
            />
            <div class="error-messages" *ngIf="isFieldInvalid('password')">
              <small *ngIf="userForm.get('password')?.errors?.['required']">
                Password is required
              </small>
              <small *ngIf="userForm.get('password')?.errors?.['minlength']">
                Password must be at least 8 characters long
              </small>
              <small *ngIf="userForm.get('password')?.errors?.['pattern']">
                Password must contain at least one uppercase, one lowercase, one number, and one special character
              </small>
            </div>
          </div>

          <!-- Phone Field -->
          <div class="form-group">
            <label for="phone" class="form-label">
              Phone Number <span class="required">*</span>
            </label>
            <input
              id="phone"
              type="tel"
              formControlName="phone"
              class="form-control"
              [class.error]="isFieldInvalid('phone')"
              placeholder="Enter your phone number"
            />
            <div class="error-messages" *ngIf="isFieldInvalid('phone')">
              <small *ngIf="userForm.get('phone')?.errors?.['required']">
                Phone number is required
              </small>
              <small *ngIf="userForm.get('phone')?.errors?.['pattern']">
                Please enter a valid phone number (10 digits)
              </small>
            </div>
          </div>

          <!-- Address Field -->
          <div class="form-group">
            <label for="address" class="form-label">
              Address <span class="required">*</span>
            </label>
            <textarea
              id="address"
              formControlName="address"
              class="form-control"
              [class.error]="isFieldInvalid('address')"
              placeholder="Enter your address"
              rows="3"
            ></textarea>
            <div class="error-messages" *ngIf="isFieldInvalid('address')">
              <small *ngIf="userForm.get('address')?.errors?.['required']">
                Address is required
              </small>
              <small *ngIf="userForm.get('address')?.errors?.['minlength']">
                Address must be at least 10 characters long
              </small>
            </div>
          </div>

          <!-- Submit Message -->
          <div *ngIf="submitMessage()" class="submit-message" [class.success]="submitMessage()?.success">
            {{ submitMessage()?.message }}
          </div>

          <!-- Form Actions -->
          <div class="form-actions">
            <button 
              type="submit" 
              class="btn btn-primary"
              [disabled]="userForm.invalid || isSubmitting()"
            >
              {{ isSubmitting() ? 'Submitting...' : 'Submit' }}
            </button>
            <button 
              type="button" 
              class="btn btn-secondary"
              (click)="onReset()"
              [disabled]="isSubmitting()"
            >
              Reset
            </button>
          </div>

          <!-- Form Status -->
          <div class="form-status">
            <p>Form Status: 
              <span [class]="userForm.valid ? 'status-valid' : 'status-invalid'">
                {{ userForm.valid ? 'Valid' : 'Invalid' }}
              </span>
            </p>
            <p>Form Touched: <span>{{ userForm.touched }}</span></p>
            <p>Form Dirty: <span>{{ userForm.dirty }}</span></p>
          </div>

        </form>
      </div>
    </div>
  `,
  styles: [`
    .form-container {
      min-height: 100vh;
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      padding: 2rem;
      font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
    }

    .form-wrapper {
      max-width: 600px;
      margin: 0 auto;
      background: white;
      border-radius: 12px;
      box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
      padding: 2.5rem;
    }

    .form-title {
      font-size: 2rem;
      font-weight: 700;
      color: #2d3748;
      margin-bottom: 2rem;
      text-align: center;
    }

    .user-form {
      display: flex;
      flex-direction: column;
      gap: 1.5rem;
    }

    .form-group {
      display: flex;
      flex-direction: column;
      gap: 0.5rem;
    }

    .form-label {
      font-size: 0.95rem;
      font-weight: 600;
      color: #4a5568;
      display: flex;
      align-items: center;
      gap: 0.25rem;
    }

    .required {
      color: #e53e3e;
      font-size: 1.1rem;
    }

    .form-control {
      padding: 0.75rem 1rem;
      font-size: 1rem;
      border: 2px solid #e2e8f0;
      border-radius: 8px;
      transition: all 0.3s ease;
      outline: none;
      font-family: inherit;
    }

    .form-control:focus {
      border-color: #667eea;
      box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
    }

    .form-control.error {
      border-color: #e53e3e;
    }

    .form-control.error:focus {
      box-shadow: 0 0 0 3px rgba(229, 62, 62, 0.1);
    }

    textarea.form-control {
      resize: vertical;
      min-height: 80px;
    }

    .error-messages {
      display: flex;
      flex-direction: column;
      gap: 0.25rem;
    }

    .error-messages small {
      color: #e53e3e;
      font-size: 0.875rem;
      display: flex;
      align-items: center;
      gap: 0.25rem;
    }

    .error-messages small::before {
      content: '⚠';
      font-size: 0.9rem;
    }

    .submit-message {
      padding: 1rem;
      border-radius: 8px;
      font-weight: 500;
      text-align: center;
      animation: slideIn 0.3s ease;
    }

    .submit-message.success {
      background-color: #c6f6d5;
      color: #22543d;
      border: 1px solid #9ae6b4;
    }

    .submit-message:not(.success) {
      background-color: #fed7d7;
      color: #742a2a;
      border: 1px solid #fc8181;
    }

    @keyframes slideIn {
      from {
        opacity: 0;
        transform: translateY(-10px);
      }
      to {
        opacity: 1;
        transform: translateY(0);
      }
    }

    .form-actions {
      display: flex;
      gap: 1rem;
      margin-top: 1rem;
    }

    .btn {
      flex: 1;
      padding: 0.875rem 1.5rem;
      font-size: 1rem;
      font-weight: 600;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      transition: all 0.3s ease;
      outline: none;
    }

    .btn-primary {
      background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
      color: white;
    }

    .btn-primary:hover:not(:disabled) {
      transform: translateY(-2px);
      box-shadow: 0 10px 20px rgba(102, 126, 234, 0.3);
    }

    .btn-primary:active:not(:disabled) {
      transform: translateY(0);
    }

    .btn-primary:disabled {
      opacity: 0.6;
      cursor: not-allowed;
    }

    .btn-secondary {
      background: #e2e8f0;
      color: #4a5568;
    }

    .btn-secondary:hover:not(:disabled) {
      background: #cbd5e0;
      transform: translateY(-2px);
      box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    }

    .btn-secondary:active:not(:disabled) {
      transform: translateY(0);
    }

    .btn-secondary:disabled {
      opacity: 0.6;
      cursor: not-allowed;
    }

    .form-status {
      margin-top: 2rem;
      padding: 1rem;
      background: #f7fafc;
      border-radius: 8px;
      font-size: 0.875rem;
      border: 1px solid #e2e8f0;
    }

    .form-status p {
      margin: 0.5rem 0;
      color: #4a5568;
    }

    .status-valid {
      color: #22543d;
      font-weight: 600;
    }

    .status-invalid {
      color: #742a2a;
      font-weight: 600;
    }

    @media (max-width: 640px) {
      .form-container {
        padding: 1rem;
      }

      .form-wrapper {
        padding: 1.5rem;
      }

      .form-title {
        font-size: 1.5rem;
      }

      .form-actions {
        flex-direction: column;
      }
    }
  `]
})
export class FormsComponent {
  userForm: FormGroup;
  
  // Signals for reactive state management
  isSubmitting = signal<boolean>(false);
  submitMessage = signal<FormSubmitResult | null>(null);

  constructor(private readonly fb: FormBuilder) {
    this.userForm = this.initializeForm();
  }

  /**
   * Initialize the form with validation rules
   */
  private initializeForm(): FormGroup {
    return this.fb.group({
      name: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(50)
        ]
      ],
      email: [
        '',
        [
          Validators.required,
          Validators.email
        ]
      ],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]/)
        ]
      ],
      phone: [
        '',
        [
          Validators.required,
          Validators.pattern(/^\d{10}$/)
        ]
      ],
      address: [
        '',
        [
          Validators.required,
          Validators.minLength(10)
        ]
      ]
    });
  }

  /**
   * Check if a field is invalid and has been touched or dirty
   */
  isFieldInvalid(fieldName: string): boolean {
    const field = this.userForm.get(fieldName);
    return !!(field && field.invalid && (field.dirty || field.touched));
  }

  /**
   * Handle form submission
   */
  onSubmit(): void {
    // Mark all fields as touched to trigger validation messages
    this.markFormGroupTouched(this.userForm);

    if (this.userForm.valid) {
      this.isSubmitting.set(true);
      
      const formData: UserFormData = this.userForm.value;
      
      // Simulate API call with setTimeout
      setTimeout(() => {
        console.log('Form Submitted:', formData);
        
        this.submitMessage.set({
          success: true,
          message: 'Form submitted successfully!',
          data: formData
        });
        
        this.isSubmitting.set(false);
        
        // Clear success message after 5 seconds
        setTimeout(() => {
          this.submitMessage.set(null);
        }, 5000);
        
        // Optionally reset the form after successful submission
        // this.onReset();
      }, 1500);
    } else {
      this.submitMessage.set({
        success: false,
        message: 'Please fix the errors in the form before submitting.'
      });
      
      setTimeout(() => {
        this.submitMessage.set(null);
      }, 5000);
    }
  }

  /**
   * Reset the form to its initial state
   */
  onReset(): void {
    this.userForm.reset();
    this.submitMessage.set(null);
    
    // Reset all form controls to pristine and untouched state
    Object.keys(this.userForm.controls).forEach(key => {
      const control = this.userForm.get(key);
      control?.setErrors(null);
    });
  }

  /**
   * Mark all fields in a form group as touched
   */
  private markFormGroupTouched(formGroup: FormGroup): void {
    Object.keys(formGroup.controls).forEach(key => {
      const control = formGroup.get(key);
      control?.markAsTouched();

      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });
  }
}