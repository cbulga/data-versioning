insert into xprjauser (XPRJAUSEID,
                       XPRJAUSEUSERNAME,
                       XPRJAUSEPASSWORD,
                       XPRJAUSENAME,
                       XPRJAUSESURNAME,
                       XPRJAUSEVERSION)
select sxprjauser.nextval,
       'administrator',
       'geocall',
       'Mario',
       'Rossi',
       0
FROM dual;