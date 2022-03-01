ALTER SESSION SET CONTAINER=XEPDB1;

create or replace directory mydatapumpdir as '/opt/oracle/mydatapump'
/
create role geocall_role not identified
/
grant create job to geocall_role
/
grant create materialized view to geocall_role
/
grant create procedure to geocall_role
/
grant create sequence to geocall_role
/
grant create session to geocall_role
/
grant create synonym to geocall_role
/
grant create table to geocall_role
    /
    grant create trigger to geocall_role
/
grant create type to geocall_role
/
grant create view to geocall_role
    /

    grant select_catalog_role to geocall_role
    /

create profile geocall_profile limit sessions_per_user default cpu_per_session default cpu_per_call default connect_time default idle_time default logical_reads_per_session default logical_reads_per_call default composite_limit default private_sga default failed_login_attempts unlimited password_life_time unlimited password_reuse_time default password_reuse_max default password_lock_time default password_grace_time default password_verify_function default
/

create tablespace geodata
    datafile '/opt/oracle/oradata/XE/XEPDB1/geodata01.dbf'
                 size 1 m
                 autoextend on next 8 k maxsize 256 m
    logging
    default
    no inmemory
    online
    extent management local autoallocate
    blocksize 8 k
    segment space management auto
    flashback on
/

create tablespace geoindx
    datafile '/opt/oracle/oradata/XE/XEPDB1/geoindx01.dbf'
                 size 1 m
                 autoextend on next 8 k maxsize 256 m
    logging
    default
    no inmemory
    online
    extent management local autoallocate
    blocksize 8 k
    segment space management auto
    flashback on
/

create tablespace geolob
    datafile '/opt/oracle/oradata/XE/XEPDB1/geolob01.dbf'
                 size 1 m
                 autoextend on next 8 k maxsize 256 m
    logging
    default
    no inmemory
    online
    extent management local autoallocate
    blocksize 8 k
    segment space management auto
    flashback on
/

create user gcdevelop identified by "gcdevelop"
    default tablespace geodata
    temporary tablespace temp
    profile geocall_profile
    account unlock
/

grant geocall_role to gcdevelop
/

alter user gcdevelop
    default role all
/

alter user gcdevelop
    quota unlimited on geodata
/

alter user gcdevelop
    quota unlimited on geoindx
/

alter user gcdevelop
    quota unlimited on geolob
/

exit
/