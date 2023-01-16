import { render, screen } from '@testing-library/react';
import BudgetItemForm from './BudgetItemForm';
describe('BudgetItemForm component', () => {
  it('should render the Button component correctly', () => {
    render(<BudgetItemForm />);
    const element = screen.getByRole('button');
    expect(element).toBeInTheDocument();
  });
});
