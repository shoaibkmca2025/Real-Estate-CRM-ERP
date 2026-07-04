import { useEffect, useState, type FormEvent } from 'react';
import { toast } from 'react-toastify';
import { useAuth } from '../../context/AuthContext';
import {
  getUserDetails,
  updateUserProfile,
  updateCompanyProfile,
  type ProfileDetails,
} from '../../api/userApi';
import { normaliseWebsite } from '../../api/companyApi';
import Loader from '../../components/common/Loader';

const EMPTY: ProfileDetails = {
  userId: 0, userName: '', userEmail: '', userMobile: '', companyName: '',
  companyEmail: '', companyMobile: '', website: '', landline: '', address: '', logoPath: '',
};

/** User / Company profile — userProfile.html + editUserProfile.html / userProfileCtrl.js. */
export default function UserProfile() {
  const { session } = useAuth();
  const [loading, setLoading] = useState(false);
  const [form, setForm] = useState<ProfileDetails>(EMPTY);
  const [logoFile, setLogoFile] = useState<File | null>(null);
  const [logoPreview, setLogoPreview] = useState<string>('');

  const load = () => {
    if (!session) return;
    setLoading(true);
    getUserDetails(session.userId)
      .then((res) => {
        if (res.code === 200 && res.object) {
          setForm({ ...EMPTY, ...res.object });
          if (res.object.logoPath && session.constantFilePath) {
            setLogoPreview(session.constantFilePath + res.object.logoPath);
          }
        }
      })
      .catch(() => toast.error('Unable to load profile.'))
      .finally(() => setLoading(false));
  };
  useEffect(load, [session]);

  const set = (k: keyof ProfileDetails, v: string) => setForm((f) => ({ ...f, [k]: v }));

  const onLogo = (file?: File) => {
    if (!file) return;
    const ext = file.name.split('.').pop()?.toLowerCase();
    if (!['jpg', 'jpeg', 'png'].includes(ext ?? '')) {
      toast.error('Invalid File Format');
      return;
    }
    setLogoFile(file);
    setLogoPreview(URL.createObjectURL(file));
  };

  const submit = async (e: FormEvent) => {
    e.preventDefault();
    if (!session) return;
    if (!window.confirm('Are you sure?')) return;
    setLoading(true);
    try {
      const userRes = await updateUserProfile({
        userId: form.userId,
        userName: form.userName,
        userEmail: form.userEmail,
        userMobile: form.userMobile,
      });
      if (userRes.code !== 200) {
        toast.error(userRes.message || 'Unable to update profile.');
        return;
      }
      const companyPayload = {
        companyId: session.companyId,
        companyName: form.companyName,
        companyEmail: form.companyEmail,
        mobile: form.companyMobile,
        website: normaliseWebsite(form.website),
        landline: form.landline,
        address: form.address,
        userId: form.userId,
        logoPath: form.logoPath,
      };
      const compRes = await updateCompanyProfile(companyPayload, logoFile);
      if (compRes.code === 200) {
        toast.success(compRes.message || 'Profile updated.');
        load();
      } else {
        toast.error(compRes.message || 'Unable to update company profile.');
      }
    } catch {
      toast.error('Unable to update profile.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={submit}>
      <Loader show={loading} />
      <div className="rc-card-title"><i className="fa fa-id-card me-2" />User Profile</div>
      <div className="row g-3">
        <div className="col-md-4">
          <div className="rc-card text-center">
            <div className="mb-3">
              {logoPreview ? (
                <img src={logoPreview} alt="logo" style={{ maxHeight: 120, maxWidth: '100%' }} />
              ) : (
                <div className="text-muted py-4"><i className="fa fa-image fa-3x" /></div>
              )}
            </div>
            <label className="btn btn-sm btn-outline-primary">
              <i className="fa fa-upload me-1" />Upload Logo
              <input type="file" accept=".jpg,.jpeg,.png" hidden onChange={(e) => onLogo(e.target.files?.[0])} />
            </label>
          </div>
        </div>
        <div className="col-md-8">
          <div className="rc-card">
            <div className="row g-3">
              <Field col={6} label="User Name" value={form.userName} onChange={(v) => set('userName', v)} required />
              <Field col={6} label="User Email" type="email" value={form.userEmail} onChange={(v) => set('userEmail', v)} required />
              <Field col={6} label="User Mobile" value={form.userMobile} onChange={(v) => set('userMobile', v)} required />
              <Field col={6} label="Company Name" value={form.companyName} onChange={(v) => set('companyName', v)} required />
              <Field col={6} label="Company Email" type="email" value={form.companyEmail} onChange={(v) => set('companyEmail', v)} />
              <Field col={6} label="Company Mobile" value={form.companyMobile} onChange={(v) => set('companyMobile', v)} />
              <Field col={6} label="Website" value={form.website} onChange={(v) => set('website', v)} />
              <Field col={6} label="Landline" value={form.landline} onChange={(v) => set('landline', v)} />
              <Field col={12} label="Address" value={form.address} onChange={(v) => set('address', v)} />
            </div>
          </div>
          <div className="rc-card">
            <button type="submit" className="btn btn-success"><i className="fa fa-check me-1" />Update Profile</button>
          </div>
        </div>
      </div>
    </form>
  );
}

function Field({
  col, label, value, onChange, type = 'text', required = false,
}: {
  col: number; label: string; value: string; onChange: (v: string) => void; type?: string; required?: boolean;
}) {
  return (
    <div className={`col-sm-${col}`}>
      <label className="form-label">{label}{required && ' *'}</label>
      <input className="form-control" type={type} value={value} onChange={(e) => onChange(e.target.value)} required={required} />
    </div>
  );
}
