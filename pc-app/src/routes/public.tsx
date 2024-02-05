import { SpecialLectureCreate } from '@/feature/lecture/routes/SpecialLectureCreate';
import { lazyImport } from '@/utils/lazyImport';
import { Navigate } from 'react-router-dom';

const { AuthRoutes } = lazyImport(() => import('@/feature/auth'), 'AuthRoutes');

export const publicRoutes = [
  {
    path: '/auth/*',
    element: <AuthRoutes />,
  },
  {
    path: '/lecture/*',
    element: <SpecialLectureCreate />,
  },

  { path: '*', element: <Navigate to="/auth/login" /> },
];
