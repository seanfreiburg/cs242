# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create

svn_log_file = File.open('db/data/svn_log_small.xml')
svn_log_hash = Hash.from_xml(svn_log_file.read)

svn_list_file = File.open('db/data/svn_list_small.xml')
svn_list_hash = Hash.from_xml(svn_list_file.read)

for entry in svn_list_hash['lists']['list']['entry']
  puts entry
  if entry['kind'] == 'file'
    FileRecord.create(name: entry['name'], size: entry['size'].to_i )
  end
end


