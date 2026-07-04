import dayjs from 'dayjs';

/**
 * Indian-numbering currency format — direct replacement for the legacy
 * AngularJS `INR` filter (groups as 12,34,567.89).
 */
export function formatINR(input: number | string | null | undefined): string {
  if (input === null || input === undefined || input === '') return '';
  const num = typeof input === 'string' ? parseFloat(input) : input;
  if (isNaN(num)) return '';

  const parts = num.toFixed(2).split('.');
  let intPart = parts[0];
  const decPart = parts[1];
  const negative = intPart.startsWith('-');
  if (negative) intPart = intPart.slice(1);

  const lastThree = intPart.length > 3 ? intPart.slice(-3) : intPart;
  const otherNumbers = intPart.length > 3 ? intPart.slice(0, -3) : '';
  const formatted =
    (otherNumbers ? otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ',') + ',' : '') + lastThree;

  return `${negative ? '-' : ''}${formatted}.${decPart}`;
}

/** Format a date value to dd/MM/yyyy (the format used throughout the app). */
export function formatDate(value: string | Date | null | undefined): string {
  if (!value) return '';
  const d = dayjs(value);
  return d.isValid() ? d.format('DD/MM/YYYY') : String(value);
}

/** Format date + time. */
export function formatDateTime(value: string | Date | null | undefined): string {
  if (!value) return '';
  const d = dayjs(value);
  return d.isValid() ? d.format('DD/MM/YYYY hh:mm A') : String(value);
}

/** Word-aware truncate — replacement for the legacy `cut` filter. */
export function truncate(value: string, max: number, tail = ' …'): string {
  if (!value) return '';
  if (value.length <= max) return value;
  let out = value.substring(0, max);
  const lastSpace = out.lastIndexOf(' ');
  if (lastSpace !== -1) out = out.substring(0, lastSpace);
  return out + tail;
}
