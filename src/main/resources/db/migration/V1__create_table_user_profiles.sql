CREATE TABLE tb_user_profiles (
                                  id UUID PRIMARY KEY,
                                  name VARCHAR(150) NOT NULL,
                                  cpf VARCHAR(11) UNIQUE,
                                  phone VARCHAR(20),
                                  address TEXT,
                                  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_user_profiles_cpf ON tb_user_profiles(cpf);