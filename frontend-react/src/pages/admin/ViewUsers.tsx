import { useEffect, useMemo, useState, type FormEvent } from 'react';
import { Modal } from 'react-bootstrap';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import {
  getAllUsers,
  getUserById,
  addUser,
  updateUser,
  updateUserStatus,
  type UserRow,
} from '../../api/userApi';
import DataTable, { type Column } from '../../components/common/DataTable';
import Loader from '../../components/common/Loader';

interface UserForm {
  userId?: number;
  userType: string;
  userName: string;
  userEmail: string;
  userMobile: string;
  userPassword?: string;
}
const EMPTY: UserForm = { userType: '', userName: '', userEmail: '', userMobile: '', userPassword: '' };

/** Users list + add/edit + status toggle — viewUsers.html (AddUserDetailsModal) / userCtrl.js. */
export default function ViewUsers() {
  const { session } = useAuth();
  const [loading, setLoading] = useState(false);
  const [users, setUsers] = useState<UserRow[]>([]);
  const [statusFilter, setStatusFilter] = useState('');
  const [show, setShow] = useState(false);
  const [editingId, setEditingId] = useState(0);
  const [form, setForm] = useState<UserForm>(EMPTY);
  const [confirm, setConfirm] = useState('');

  const load = () => {
    if (!session) return;
    setLoading(true);
    getAllUsers(session.companyId ?? '')
      .then((res) => setUsers(res.code === 200 ? res.list ?? [] : []))
      .catch(() => toast.error('Unable to load users.'))
      .finally(() => setLoading(false));
  };
  useEffect(load, [session]);

  const filtered = useMemo(
    () => (statusFilter === '' ? users : users.filter((u) => String(u.userStatus) === statusFilter)),
    [users, statusFilter]
  );

  const openModal = async (userId: number) => {
    setEditingId(userId);
    setConfirm('');
    if (userId === 0) {
      setForm(EMPTY);
    } else {
      setLoading(true);
      try {
        const res = await getUserById(userId);
        if (res.code === 200 && res.object) {
          const u = res.object;
          setForm({ userId: u.userId, userType: String(u.userType), userName: u.userName, userEmail: u.userEmail, userMobile: u.userMobile });
        }
      } finally {
        setLoading(false);
      }
    }
    setShow(true);
  };

  const set = (k: keyof UserForm, v: string) => setForm((f) => ({ ...f, [k]: v }));

  const submit = async (e: FormEvent) => {
    e.preventDefault();
    if (editingId === 0 && form.userPassword !== confirm) {
      toast.error('Passwords do not match.');
      return;
    }
    setLoading(true);
    try {
      const payload = { ...form, companyId: session?.companyId };
      const res = editingId === 0 ? await addUser(payload) : await updateUser({ ...payload, userId: editingId });
      if (res.code === 200) {
        toast.success(res.message || 'Saved.');
        setShow(false);
        load();
      } else {
        toast.error(res.message || 'Unable to save user.');
      }
    } catch {
      toast.error('Unable to save user.');
    } finally {
      setLoading(false);
    }
  };

  const toggleStatus = async (u: UserRow) => {
    setLoading(true);
    try {
      const res = await updateUserStatus(u.userId, u.userStatus === 1 ? 0 : 1);
      toast[res.code === 200 ? 'success' : 'error'](res.message || '');
      load();
    } finally {
      setLoading(false);
    }
  };

  const columns: Column<UserRow>[] = [
    { key: 'userName', header: 'User Name' },
    { key: 'userEmail', header: 'Email' },
    { key: 'userMobile', header: 'Mobile' },
    {
      key: 'userType', header: 'User Type',
      render: (u) => u.userType === 2
        ? <span className="label label-info">Admin</span>
        : <span className="label label-default">SubUser</span>,
    },
    {
      key: 'userStatus', header: 'User Status',
      render: (u) => u.userStatus === 1
        ? <span className="label label-success">Active</span>
        : <span className="label label-danger">Inactive</span>,
    },
    {
      key: 'action', header: 'Action', sortable: false,
      render: (u) => (
        <span className="d-inline-flex align-items-center gap-2">
          <input type="checkbox" title="Update Status" checked={u.userStatus === 1} onChange={() => toggleStatus(u)} />
          <a role="button" className="green" title="Edit" onClick={() => openModal(u.userId)}><i className="fa fa-pencil" /></a>
        </span>
      ),
    },
  ];

  return (
    <div className="rc-card">
      <Loader show={loading} />
      <div className="d-flex justify-content-between align-items-center mb-3">
        <div className="rc-card-title mb-0"><i className="fa fa-users me-2" />View Users</div>
        <button className="btn btn-info btn-sm text-white" onClick={() => openModal(0)}><i className="fa fa-plus me-1" />Add User</button>
      </div>

      <div className="row mb-3">
        <div className="col-sm-3">
          <select className="form-select" value={statusFilter} onChange={(e) => setStatusFilter(e.target.value)}>
            <option value="">All</option>
            <option value="1">Active</option>
            <option value="0">Inactive</option>
          </select>
        </div>
      </div>

      <DataTable columns={columns} rows={filtered} searchKeys={['userName', 'userEmail', 'userMobile']} />

      <Modal show={show} onHide={() => setShow(false)} centered>
        <Modal.Header closeButton><Modal.Title>{editingId === 0 ? 'Add' : 'Update'} User Details</Modal.Title></Modal.Header>
        <form onSubmit={submit}>
          <Modal.Body>
            <div className="mb-3">
              <label className="form-label">User Type</label>
              <select className="form-select" value={form.userType} onChange={(e) => set('userType', e.target.value)} required>
                <option value="">Click to Choose...</option>
                <option value="2">Admin</option>
                <option value="3">Subuser</option>
              </select>
            </div>
            <Field label="User Name" value={form.userName} onChange={(v) => set('userName', v)} required />
            <Field label="User Email" type="email" value={form.userEmail} onChange={(v) => set('userEmail', v)} required />
            <Field label="Contact Number" value={form.userMobile} onChange={(v) => set('userMobile', v)} required />
            {editingId === 0 && (
              <>
                <Field label="User Password" type="password" value={form.userPassword ?? ''} onChange={(v) => set('userPassword', v)} required />
                <Field label="Confirm Password" type="password" value={confirm} onChange={setConfirm} required />
              </>
            )}
          </Modal.Body>
          <Modal.Footer>
            <button type="submit" className="btn btn-sm btn-primary"><i className="fa fa-check me-1" />{editingId === 0 ? 'Add' : 'Update'}</button>
            <button type="button" className="btn btn-sm btn-danger" onClick={() => setShow(false)}><i className="fa fa-times me-1" />Cancel</button>
          </Modal.Footer>
        </form>
      </Modal>
    </div>
  );
}

function Field({
  label, value, onChange, type = 'text', required = false,
}: {
  label: string; value: string; onChange: (v: string) => void; type?: string; required?: boolean;
}) {
  return (
    <div className="mb-3">
      <label className="form-label">{label}</label>
      <input className="form-control" type={type} value={value} onChange={(e) => onChange(e.target.value)} required={required} />
    </div>
  );
}
