import { useMemo, useState, type ReactNode } from 'react';

/**
 * Lightweight, themeable data table — replaces the legacy
 * DataTables + dirPagination combo. Supports client-side sorting, search and
 * pagination while keeping the original striped/bordered look and the
 * "Sr.No." + sort-chevron behaviour.
 */
export interface Column<T> {
  /** key into the row, or a custom id when using `render` */
  key: keyof T | string;
  header: string;
  /** custom cell renderer */
  render?: (row: T, index: number) => ReactNode;
  /** disable sorting for this column */
  sortable?: boolean;
  className?: string;
}

interface DataTableProps<T> {
  columns: Column<T>[];
  rows: T[];
  /** show the auto-incrementing Sr.No. column (on by default, as in the legacy tables) */
  serialColumn?: boolean;
  /** fields to match against the search box */
  searchKeys?: (keyof T)[];
  pageSize?: number;
  emptyText?: string;
  /** optional toolbar rendered to the right of the search box (e.g. "Add" button) */
  toolbar?: ReactNode;
}

export default function DataTable<T extends object>({
  columns,
  rows,
  serialColumn = true,
  searchKeys,
  pageSize = 10,
  emptyText = 'No data available in table',
  toolbar,
}: DataTableProps<T>) {
  const [search, setSearch] = useState('');
  const [sortKey, setSortKey] = useState<string | null>(null);
  const [reverse, setReverse] = useState(false);
  const [page, setPage] = useState(1);

  const filtered = useMemo(() => {
    if (!search.trim()) return rows;
    const term = search.toLowerCase();
    const keys = searchKeys ?? (columns.map((c) => c.key) as (keyof T)[]);
    return rows.filter((row) =>
      keys.some((k) => String(row[k] ?? '').toLowerCase().includes(term))
    );
  }, [rows, search, searchKeys, columns]);

  const sorted = useMemo(() => {
    if (!sortKey) return filtered;
    const copy = [...filtered];
    copy.sort((a, b) => {
      const av = a[sortKey as keyof T];
      const bv = b[sortKey as keyof T];
      if (av == null) return 1;
      if (bv == null) return -1;
      if (typeof av === 'number' && typeof bv === 'number') return av - bv;
      return String(av).localeCompare(String(bv), undefined, { numeric: true });
    });
    return reverse ? copy.reverse() : copy;
  }, [filtered, sortKey, reverse]);

  const totalPages = Math.max(1, Math.ceil(sorted.length / pageSize));
  const current = Math.min(page, totalPages);
  const pageRows = sorted.slice((current - 1) * pageSize, current * pageSize);

  const toggleSort = (key: string) => {
    if (sortKey === key) setReverse((r) => !r);
    else {
      setSortKey(key);
      setReverse(false);
    }
  };

  const colSpan = columns.length + (serialColumn ? 1 : 0);

  return (
    <div className="data-table">
      <div className="d-flex justify-content-between align-items-center mb-2 gap-2 flex-wrap">
        <input
          type="text"
          className="form-control"
          style={{ maxWidth: 260 }}
          placeholder="Search..."
          value={search}
          onChange={(e) => {
            setSearch(e.target.value);
            setPage(1);
          }}
        />
        {toolbar}
      </div>

      <div className="table-responsive">
        <table className="table table-striped table-condensed table-bordered">
          <thead>
            <tr>
              {serialColumn && <th className="text-center">Sr.No.</th>}
              {columns.map((col) => (
                <th
                  key={String(col.key)}
                  className={`text-center ${col.className ?? ''}`}
                  style={{ cursor: col.sortable === false ? 'default' : 'pointer' }}
                  onClick={() => col.sortable !== false && toggleSort(String(col.key))}
                >
                  {col.header}
                  {col.sortable !== false && sortKey === col.key && (
                    <span className={`sort-icon ms-1 fa ${reverse ? 'fa-chevron-up' : 'fa-chevron-down'}`} />
                  )}
                </th>
              ))}
            </tr>
          </thead>
          <tbody>
            {pageRows.length === 0 ? (
              <tr>
                <td colSpan={colSpan} className="text-center">{emptyText}</td>
              </tr>
            ) : (
              pageRows.map((row, i) => (
                <tr key={i}>
                  {serialColumn && (
                    <td className="text-center">{(current - 1) * pageSize + i + 1}</td>
                  )}
                  {columns.map((col) => (
                    <td key={String(col.key)} className={col.className}>
                      {col.render ? col.render(row, i) : String(row[col.key as keyof T] ?? '')}
                    </td>
                  ))}
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>

      {totalPages > 1 && (
        <div className="d-flex justify-content-end align-items-center gap-2">
          <button className="btn btn-sm btn-outline-secondary" disabled={current === 1} onClick={() => setPage(1)}>«</button>
          <button className="btn btn-sm btn-outline-secondary" disabled={current === 1} onClick={() => setPage(current - 1)}>‹</button>
          <span className="text-muted">Page {current} of {totalPages}</span>
          <button className="btn btn-sm btn-outline-secondary" disabled={current === totalPages} onClick={() => setPage(current + 1)}>›</button>
          <button className="btn btn-sm btn-outline-secondary" disabled={current === totalPages} onClick={() => setPage(totalPages)}>»</button>
        </div>
      )}
    </div>
  );
}
